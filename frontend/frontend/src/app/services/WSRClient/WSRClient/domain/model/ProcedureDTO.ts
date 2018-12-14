export class ProcedureDTO<LT, D> {
    private type: LT;
    private data: D;

    public getType(): LT {
        return this.type;
    }

    public setType(type: LT): void {
        this.type = type;
    }

    public getData(): D {
        return this.data;
    }

    public setData(data: D): void {
        this.data = data;
    }
}