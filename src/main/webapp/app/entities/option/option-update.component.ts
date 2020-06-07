import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IOption, Option } from 'app/shared/model/option.model';
import { OptionService } from './option.service';
import { IDepartment } from 'app/shared/model/department.model';
import { DepartmentService } from 'app/entities/department/department.service';

@Component({
  selector: 'jhi-option-update',
  templateUrl: './option-update.component.html'
})
export class OptionUpdateComponent implements OnInit {
  isSaving = false;

  departments: IDepartment[] = [];

  editForm = this.fb.group({
    id: [],
    optionName: [],
    status: [],
    departmentId: []
  });

  constructor(
    protected optionService: OptionService,
    protected departmentService: DepartmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ option }) => {
      this.updateForm(option);

      this.departmentService
        .query()
        .pipe(
          map((res: HttpResponse<IDepartment[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IDepartment[]) => (this.departments = resBody));
    });
  }

  updateForm(option: IOption): void {
    this.editForm.patchValue({
      id: option.id,
      optionName: option.optionName,
      status: option.status,
      departmentId: option.departmentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const option = this.createFromForm();
    if (option.id !== undefined) {
      this.subscribeToSaveResponse(this.optionService.update(option));
    } else {
      this.subscribeToSaveResponse(this.optionService.create(option));
    }
  }

  private createFromForm(): IOption {
    return {
      ...new Option(),
      id: this.editForm.get(['id'])!.value,
      optionName: this.editForm.get(['optionName'])!.value,
      status: this.editForm.get(['status'])!.value,
      departmentId: this.editForm.get(['departmentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOption>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IDepartment): any {
    return item.id;
  }
}
