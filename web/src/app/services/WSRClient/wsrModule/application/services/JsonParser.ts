
export class JsonParser<LT extends string> {

    public parse<DATA>(json: string, dataObject: DATA) :DATA{
        console.log(json,dataObject, "IN JSON PARSER")
        return Object.assign(dataObject,JSON.parse(json))
    }

}