package com.ManagementTugas.ManagementTugas.repository;

import java.util.*;

import com.ManagementTugas.ManagementTugas.model.TugasSaya;

public interface InterCustomeTaskRepo {

    List<TugasSaya> findBynamatugas(String namatugas);

    // Optional<TugasSaya> findByidtugas(String idtugas);

}
