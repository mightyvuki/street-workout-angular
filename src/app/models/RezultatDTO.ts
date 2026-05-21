import { DisciplinaDTO } from "./DisciplinaDTO";
import { KategorijaDTO } from "./KategorijaDTO";
import { KorisnikDTO } from "./KorisnikDTO";
import { TakmicenjeDTO } from "./TakmicenjeDTO";

/*export interface RezultatDTO {
    id: number;
    rezultat: number;
    disciplina: DisciplinaDTO;
    kategorija: KategorijaDTO;
    korisnik: KorisnikDTO;
    takmicenje: TakmicenjeDTO;
}*/

export interface RezultatDTO {
    id: number;
    rezultat: number;
    nazivTakmicenja: string;
    nazivDiscipline: string;
    imeIPrezime: string;
}