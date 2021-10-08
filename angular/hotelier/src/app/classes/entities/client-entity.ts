export class ClientEntity {

    public id: number | undefined;
    public nom: string | undefined;
    public telephone: string | undefined;
    public email: string | undefined;
    public adresse: string | undefined;

    constructor( id?: number | undefined, nom?: string | undefined, telephone?: string | undefined, email?: string | undefined, adresse?: string | undefined ) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

}
