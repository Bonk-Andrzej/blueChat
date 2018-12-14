export class NewProcedureDTO {
    private nameOfProcedure: string;
    private dataJson: string;

    public setNameOfProcedure(json: string):void{
        this.nameOfProcedure = json;
    }
    public setDataJson(json: string):void{
        this.dataJson = json;
    }

    public getNameOfProcedure():string{
        return this.nameOfProcedure;
    }
    public getDataJson():string{
        return this.dataJson;
    }
}