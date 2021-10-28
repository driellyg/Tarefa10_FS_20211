import { Hospede } from './../../../../model/hospede.model';
import { HospedeService } from './../../../../service/hospede.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-hospede-update',
  templateUrl: '../hospede-form/hospede-form.component.html',
  styleUrls: ['../hospede-form/hospede-form.component.css']
})
export class HospedeUpdateComponent implements OnInit {
  titulo: string = "Alterar dados do Hóspede";

  hospede: Hospede = {
    nmHospede: '',
    dtNascimento: '',
    cpf: 0,
  }

  constructor(
    private route: ActivatedRoute,
    private service: HospedeService,
    private router: Router
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    if(id != null){
      this.service.findById(id).subscribe(hospede => {
        this.hospede = hospede;
      })
    }
  }

  salvar(): void {
    this.service.update(this.hospede).subscribe(() =>{
      this.service.showMessage("Hóspede atualizado com sucesso!")
      this.router.navigate(['/hospedes']);
    });
  }

}
