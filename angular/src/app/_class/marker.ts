export class Marker {
    id!: number;
    label!:string;
    latitude: number;
    longitude: number;
    draggable!:boolean;

    
   constructor(lat:number,lng:number)
    {
        this.latitude = lat;
        this.longitude = lng;
    }
   
    set setLatitude(lat:number)
    {
        this.latitude = lat;
    }
    
    set setId(idRequest : number )
    {
        this.id = idRequest;
    }

    set setLongitude(long:any)
    {
        this.longitude = long;
    }
    

    set setLabet(labl: string )
    {
        this.label = labl;
    }

}
