export class HotelEntity {

    public id: number | undefined;
    public nom: string | undefined;
    public etoiles: number | undefined;
    public adresse: string | undefined;
    public telephone: string | undefined;
    public email: string | undefined;
    public ville: string | undefined;

    constructor( id?: number | undefined,
        nom?: string | undefined,
        etoiles?: number | undefined,
        adresse?: string | undefined,
        telephone?: string | undefined,
        email?: string | undefined,
        ville?: string | undefined ) {
        this.id = id;
        this.nom = nom;
        this.etoiles = etoiles;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.ville = ville;

    }

}
