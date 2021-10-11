import { Injectable } from "@angular/core";

@Injectable( {
    providedIn: 'root'
} )
export class ClientSorter {

    sorterByNomAsc: ( a: any, b: any ) => number =
        function ( a: any, b: any ) {
            if ( b.nom < a.nom ) return 1;
            if ( b.nom > a.nom ) return -1;
            return 0;
        };

}
