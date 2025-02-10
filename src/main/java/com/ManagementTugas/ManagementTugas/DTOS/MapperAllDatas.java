package com.ManagementTugas.ManagementTugas.DTOS;

import com.ManagementTugas.ManagementTugas.model.TugasSaya;

public class MapperAllDatas {

    public TugasSaya cretaeTugasSaya(CreateTugasBaru createTugasBaru) {
        TugasSaya tugasSaya = new TugasSaya();
        tugasSaya.setBerakhirtugas(createTugasBaru.berakhirtugas());
        tugasSaya.setCompleted(createTugasBaru.completed());
        tugasSaya.setDeskripsi(createTugasBaru.deskripsi());
        tugasSaya.setMulaitugas(createTugasBaru.mulaitugas());
        tugasSaya.setNamatugas(createTugasBaru.namatugas());
        tugasSaya.setIde_tugas(createTugasBaru.ide_tugas());
        tugasSaya.setId_tugas("111111");
        return tugasSaya;

    }
}
