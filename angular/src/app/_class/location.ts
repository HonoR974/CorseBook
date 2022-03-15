export class Location {
    latitude: number;
    longitude: number;

    constructor(lat:number,long:number)
    {
        this.latitude = lat;
        this.longitude = long;
    }

    set _latitude(lat:number)
    {
        this.latitude = lat;
    }
}
