import { ConfirmDeleteComponent } from './../../../template/confirm-delete/confirm-delete.component';
import { HospedagemService } from './../../../../service/hospedagem.service';
import { Component, OnInit } from '@angular/core';
import { Hospedagem } from 'src/app/model/hospedagem.model';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-hospedagem-list',
  templateUrl: './hospedagem-list.component.html',
  styleUrls: ['./hospedagem-list.component.css']
})
export class HospedagemListComponent implements OnInit {

  hospedagens: Hospedagem[] = [];
  displayedColumns: string[] = ['id', 'idQuarto', 'idHospede', 'dtCheckin', 'dtCheckout', 'acao'];
  constructor(
    private service : HospedagemService,
    private dialog: MatDialog
  ) { }

  atualizarDados(): void {
    this.service.findAll().subscribe(hospedagens => {
      this.hospedagens = hospedagens;
    });
  }

  ngOnInit(): void {
    this.atualizarDados();
  }

  excluir(hospedagem: Hospedagem): void{

    const dialogRef = this.dialog.open(ConfirmDeleteComponent, {
      data: {
        message: `Deseja realmente excluir a hospedagem ${hospedagem.idHospedagem}?`,
        buttonText: {
          ok: 'Excluir',
          cancel: 'Desistir'
        }
      }
    })

    dialogRef.afterClosed().subscribe((confirm: boolean) =>{
      if(confirm){
        this.service.delete(hospedagem).subscribe(() => {
          this.service.showMessage("Hospedagem excluída com sucesso!");
          this.atualizarDados();
        });
      }
    })
    
  }
}
