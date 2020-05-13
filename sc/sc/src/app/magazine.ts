export class Magazine {  
    issn: string;
    name: string;
    scienceFields: number[];
    scienceFieldNames: string[];
    membershipPrice: number;
    paymentType: MagazinePaymentType;
    mainEditorFirstName: string;
    mainEditorLastName: string;
};

export enum MagazinePaymentType{
    open_access, paid_access //samo daj na srpskom kako da napisemo to
}

  