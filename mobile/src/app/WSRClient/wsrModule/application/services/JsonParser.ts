
export class JsonParser<LT extends string> {

    public parse<DATA>(json: string, dataObject: DATA) :DATA{
        return Object.assign(dataObject,JSON.parse(json))
    }

}