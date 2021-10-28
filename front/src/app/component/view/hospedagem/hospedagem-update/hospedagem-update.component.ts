import { Hospedagem } from './../../../../model/hospedagem.model';
import { HospedagemService } from './../../../../service/hospedagem.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Hospede } from 'src/app/model/hospede.model';
import { Quarto } from 'src/app/model/quarto.model';


@Component({
  selector: 'app-hospedagem-update',
  templateUrl: '../hospedagem-form/hospedagem-form.component.html',
  styleUrls: ['../hospedagem-form/hospedagem-form.component.css']
})
export class HospedagemUpdateComponent implements OnInit {

  titulo: string = "Alterar dados da Hospedagem";

  hospedagem: Hospedagem = {
    quarto: <Quarto>{},
    hospede: <Hospede>{},
    dtCheckin: '',
    dtCheckout: '',
  }

  constructor(
    private route: ActivatedRoute,
    private service: HospedagemService,
    private router: Router
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    if(id != null){
      this.service.findById(id).subscribe(hospedagem => {
        this.hospedagem = hospedagem;
      })
    }
  }

  salvar(): void {
    this.service.update(this.hospedagem).subscribe(() =>{
      this.service.showMessage("Hospedagem atualizada sucesso!")
      this.router.navigate(['/hospedagens']);
    });
  }

}
