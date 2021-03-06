import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDeleteComponent } from 'src/app/component/template/confirm-delete/confirm-delete.component';
import { Hospede } from 'src/app/model/hospede.model';
import { HospedeService } from 'src/app/service/hospede.service';

@Component({
  selector: 'app-hospede-list',
  templateUrl: './hospede-list.component.html',
  styleUrls: ['./hospede-list.component.css']
})
export class HospedeListComponent implements OnInit {
  hospedes: Hospede[] = [];
  displayedColumns: string[] = ['id', 'nome', 'data de nascimento', 'cpf', 'acao'];
  constructor(
    private service : HospedeService,
    private dialog: MatDialog
  ) { }

  atualizarDados(): void {
    this.service.findAll().subscribe(hospedes => {
      this.hospedes = hospedes;
    });
  }

  ngOnInit(): void {
    this.atualizarDados();
  }

  excluir(hospede: Hospede): void{

    const dialogRef = this.dialog.open(ConfirmDeleteComponent, {
      data: {
        message: `Deseja realmente excluir o hóspede ${hospede.nmHospede}?`,
        buttonText: {
          ok: 'Excluir',
          cancel: 'Desistir'
        }
      }
    })

    dialogRef.afterClosed().subscribe((confirm: boolean) =>{
      if(confirm){
        this.service.delete(hospede).subscribe(() => {
          this.service.showMessage("Hóspede excluído com sucesso!");
          this.atualizarDados();
        });
      }
    })
    
  }
}
