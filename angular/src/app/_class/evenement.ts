import { FileAPI } from "./file-api";
import { Marker } from "./marker";

export class Evenement {
    id!: number;
    name!:string;
    dateDebut!:string;
    dateFin!:string;
    contenu!:string;
    listMarker: Marker[] = [];
    listFileAPI: FileAPI[] = [];
    
    //le btn toggle entre map et file 
    checkedMarker:boolean = true;
}
