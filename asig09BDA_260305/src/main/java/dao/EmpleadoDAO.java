package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Empleado;
import util.JPAUtil;

public class EmpleadoDAO {

    private EntityManager em = JPAUtil.getEntityManager();

    public void insertar(Empleado empleado) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void actualizar(Empleado empleado) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.remove(empleado);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Empleado buscar(Long id) {
        return em.find(Empleado.class, id);
    }

    public List<Empleado> listar() {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    public boolean aumentarSalario(Long id, Double porcentajeAumento) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Empleado empleado = em.find(Empleado.class, id);
            
            if (empleado != null && porcentajeAumento > 0) {

                double salarioActual = empleado.getSalario();
                double aumento = salarioActual * (porcentajeAumento / 100.0);
                empleado.setSalario(salarioActual + aumento);
                
                transaction.commit();
                return true;
            } else {

                transaction.rollback();
                return false;
            }

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}