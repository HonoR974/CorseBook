import { Marker } from "./marker";

export class Evenement {
    id!: number;
    name!:string;
    dateDebut!:string;
    dateFin!:string;
    contenu!:string;
    listMarker: Marker[] = [];
}
