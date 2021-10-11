import { ClientEntity } from "./client-entity";
import { HotelEntity } from "./hotel-entity";

export class ResaEntity {

    id: number | undefined;
    client: ClientEntity | undefined;
    hotel: HotelEntity | undefined;
    dateDeb: Date | undefined;
    dateFin: Date | undefined;
    num_chambre: number | undefined;

    constructor( id?: number | undefined,
        client?: ClientEntity | undefined,
        hotel?: HotelEntity | undefined,
        dateDeb?: Date | undefined,
        dateFin?: Date | undefined,
        num_chambre?: number | undefined ) {
        this.id = id;
        this.client = this.client;
        this.hotel = hotel;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.num_chambre = num_chambre;
    }

}
