export class InfoWebSocket {
    private message: string;
    private status: string;

    constructor() {
    }

    public getMessage(): string {
        return this.message;
    }

    public setMessage(message: string): void {
        this.message = message;
    }

    public getStatus(): string {
        return status;
    }

    public setStatus(status: string): void {
        this.status = status;
    }
}
