import { Moment } from 'moment';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDocument {
  id?: number;
  title?: string;
  defenseDate?: Moment;
  authorName?: string;
  supervisorName?: string;
  fileContentType?: string;
  file?: any;
  status?: Status;
  optionId?: number;
}

export class Document implements IDocument {
  constructor(
    public id?: number,
    public title?: string,
    public defenseDate?: Moment,
    public authorName?: string,
    public supervisorName?: string,
    public fileContentType?: string,
    public file?: any,
    public status?: Status,
    public optionId?: number
  ) {}
}
