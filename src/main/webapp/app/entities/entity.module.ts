import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'faculty',
        loadChildren: () => import('./faculty/faculty.module').then(m => m.ThesesManagerFacultyModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.ThesesManagerDepartmentModule)
      },
      {
        path: 'option',
        loadChildren: () => import('./option/option.module').then(m => m.ThesesManagerOptionModule)
      },
      {
        path: 'document',
        loadChildren: () => import('./document/document.module').then(m => m.ThesesManagerDocumentModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ThesesManagerEntityModule {}
