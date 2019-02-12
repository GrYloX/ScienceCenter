export class MerchantOrder{
    merchantOrderID : string;
    amountOrder : number;
    buyer_id : string;
    successUrl : string; 
    errorUrl : string;
    failedUrl : string;
    type : Type;
    purchaseTypeId : string;
    merchant_id : string; //issn
}
export enum Type{
    MAGAZINE_EDITION, PAPER, SUBSCRIPTION
}