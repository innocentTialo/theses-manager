import { IDocument } from 'app/shared/model/document.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IOption {
  id?: number;
  optionName?: string;
  status?: Status;
  documents?: IDocument[];
  departmentId?: number;
}

export class Option implements IOption {
  constructor(
    public id?: number,
    public optionName?: string,
    public status?: Status,
    public documents?: IDocument[],
    public departmentId?: number
  ) {}
}
