import { IDepartment } from 'app/shared/model/department.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IFaculty {
  id?: number;
  facultyName?: string;
  directorName?: string;
  status?: Status;
  departments?: IDepartment[];
}

export class Faculty implements IFaculty {
  constructor(
    public id?: number,
    public facultyName?: string,
    public directorName?: string,
    public status?: Status,
    public departments?: IDepartment[]
  ) {}
}
