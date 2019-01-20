export class Procedure<LT, D> {

    private type: LT;
    private dataObject: D;
    private method: (data: D) => void;

    constructor() {
    }


    public getMethod(): (data: D) => void {
        return this.method;
    }

    public setMethod(method: (data: D) => void): void {
        this.method = method;
    }


    public getType(): LT {
        return this.type;
    }

    public setType(type: LT): void {
        this.type = type;
    }

    public setDataObject(data: D): void {
        this.dataObject = data;
    }
    public getDataObject(): D {
        return this.dataObject;
    }

}