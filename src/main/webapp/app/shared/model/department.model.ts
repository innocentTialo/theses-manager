import { IOption } from 'app/shared/model/option.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDepartment {
  id?: number;
  departmentName?: string;
  hodName?: string;
  status?: Status;
  options?: IOption[];
  facultyId?: number;
}

export class Department implements IDepartment {
  constructor(
    public id?: number,
    public departmentName?: string,
    public hodName?: string,
    public status?: Status,
    public options?: IOption[],
    public facultyId?: number
  ) {}
}
