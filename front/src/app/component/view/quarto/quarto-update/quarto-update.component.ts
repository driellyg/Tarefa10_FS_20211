import { Quarto } from './../../../../model/quarto.model';
import { Hotel } from 'src/app/model/hotel.model';
import { QuartoService } from './../../../../service/quarto.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaQuarto } from 'src/app/enum/categoriaQuarto.enum';

@Component({
  selector: 'app-quarto-update',
  templateUrl: '../quarto-form/quarto-form.component.html',
  styleUrls: ['../quarto-form/quarto-form.component.css']
})
export class QuartoUpdateComponent implements OnInit {

  titulo: string = "Alterar dados do Quarto";

  
  quarto: Quarto = {
    idQuarto: 0,
    hotel: <Hotel> {},
    categoriaQuarto: CategoriaQuarto.SIMPLES,
    nrQuarto: 0,
    qtdLeito: 0,
    prDiaria: 0,
  }

  public categorias = Object.values(CategoriaQuarto);
  public hoteis : Hotel[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: QuartoService,    
    private router: Router
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    if(id != null){
      this.service.findById(id).subscribe(quarto => {
        this.quarto = quarto;
      })
    }
  }

  salvar(): void {
    this.service.update(this.quarto).subscribe(() =>{
      this.service.showMessage("Quarto atualizado com sucesso!")
      this.router.navigate(['/quartos']);
    });
  }

}
