package dao;

import org.springframework.stereotype.Repository;

import model.Ordenador;
import service.OrdenadorDao;

@Repository
public class OrdenadorDaoImpl 
		extends GenericDaoImpl<Ordenador, Integer> 
		implements OrdenadorDao {

}
