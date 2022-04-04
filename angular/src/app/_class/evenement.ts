import { FileAPI } from "./file-api";
import { Marker } from "./marker";
import { Comment } from "./comment";

export class Evenement {
    id!: number;
    name!:string;
    dateDebut!:string;
    dateFin!:string;
    contenu!:string;
    listMarker: Marker[] = [];
    listFileAPI: FileAPI[] = [];
    listParticipant: String[] = [];
    listComments : Comment[] = [];
    //le btn toggle entre map et file 
    checkedMarker:boolean = true;

    //l'user participe à l'event 
    isParticiped:boolean = false;

    isClickedComment : boolean = false;
}
