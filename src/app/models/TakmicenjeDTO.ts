import { KorisnikDTO } from "./KorisnikDTO";

export interface TakmicenjeDTO {
  id?: number;
  naziv?: string;
  datum?: string; 
  lokacija?: string;
  opis?: string;
  organizator?: KorisnikDTO;
}
