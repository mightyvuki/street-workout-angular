import { KorisnikDTO } from "./KorisnikDTO";
import { TakmicenjeDTO } from "./TakmicenjeDTO";

export interface PrijavaDTO {
    id: number;
    datumPrijave: string;
    takmicenje: TakmicenjeDTO;
    korisnik: KorisnikDTO;
}