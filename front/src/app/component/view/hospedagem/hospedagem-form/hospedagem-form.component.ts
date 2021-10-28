import { HospedagemService } from './../../../../service/hospedagem.service';
import { Hospedagem } from './../../../../model/hospedagem.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Quarto } from 'src/app/model/quarto.model';
import { Hospede } from 'src/app/model/hospede.model';

@Component({
  selector: 'app-hospedagem-form',
  templateUrl: './hospedagem-form.component.html',
  styleUrls: ['./hospedagem-form.component.css']
})
export class HospedagemFormComponent implements OnInit {

  titulo: string = "Cadastrar nova Hospedagem";

  hospedagem: Hospedagem = {
    quarto: <Quarto> {},
    hospede: <Hospede>{},
    dtCheckin: '',
    dtCheckout: ''
  }
  

  constructor(
    private service: HospedagemService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  salvar(): void {
    this.service.create(this.hospedagem).subscribe(() =>{
      this.service.showMessage("Hospedagem cadastrada com sucesso!")
      this.router.navigate(['/hospedagens']);
    });
  }

}
