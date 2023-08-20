package com.mycompany.escuela.persistencia;

import com.mycompany.escuela.logica.Carrera;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.escuela.logica.Materia;
import com.mycompany.escuela.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gerson Benito
 */
public class CarreraJpaController implements Serializable {

    public CarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public CarreraJpaController(){
            emf = Persistence.createEntityManagerFactory("escuelaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrera carrera) {
        if (carrera.getMateria() == null) {
            carrera.setMateria(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Materia> attachedMateria = new ArrayList<Materia>();
            for (Materia materiaMateriaToAttach : carrera.getMateria()) {
                materiaMateriaToAttach = em.getReference(materiaMateriaToAttach.getClass(), materiaMateriaToAttach.getId());
                attachedMateria.add(materiaMateriaToAttach);
            }
            carrera.setMateria(attachedMateria);
            em.persist(carrera);
            for (Materia materiaMateria : carrera.getMateria()) {
                Carrera oldCarreraOfMateriaMateria = materiaMateria.getCarrera();
                materiaMateria.setCarrera(carrera);
                materiaMateria = em.merge(materiaMateria);
                if (oldCarreraOfMateriaMateria != null) {
                    oldCarreraOfMateriaMateria.getMateria().remove(materiaMateria);
                    oldCarreraOfMateriaMateria = em.merge(oldCarreraOfMateriaMateria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrera carrera) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera persistentCarrera = em.find(Carrera.class, carrera.getId());
            ArrayList<Materia> materiaOld = persistentCarrera.getMateria();
            ArrayList<Materia> materiaNew = carrera.getMateria();
            ArrayList<Materia> attachedMateriaNew = new ArrayList<Materia>();
            for (Materia materiaNewMateriaToAttach : materiaNew) {
                materiaNewMateriaToAttach = em.getReference(materiaNewMateriaToAttach.getClass(), materiaNewMateriaToAttach.getId());
                attachedMateriaNew.add(materiaNewMateriaToAttach);
            }
            materiaNew = attachedMateriaNew;
            carrera.setMateria(materiaNew);
            carrera = em.merge(carrera);
            for (Materia materiaOldMateria : materiaOld) {
                if (!materiaNew.contains(materiaOldMateria)) {
                    materiaOldMateria.setCarrera(null);
                    materiaOldMateria = em.merge(materiaOldMateria);
                }
            }
            for (Materia materiaNewMateria : materiaNew) {
                if (!materiaOld.contains(materiaNewMateria)) {
                    Carrera oldCarreraOfMateriaNewMateria = materiaNewMateria.getCarrera();
                    materiaNewMateria.setCarrera(carrera);
                    materiaNewMateria = em.merge(materiaNewMateria);
                    if (oldCarreraOfMateriaNewMateria != null && !oldCarreraOfMateriaNewMateria.equals(carrera)) {
                        oldCarreraOfMateriaNewMateria.getMateria().remove(materiaNewMateria);
                        oldCarreraOfMateriaNewMateria = em.merge(oldCarreraOfMateriaNewMateria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = carrera.getId();
                if (findCarrera(id) == null) {
                    throw new NonexistentEntityException("The carrera with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera;
            try {
                carrera = em.getReference(Carrera.class, id);
                carrera.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrera with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Materia> materia = carrera.getMateria();
            for (Materia materiaMateria : materia) {
                materiaMateria.setCarrera(null);
                materiaMateria = em.merge(materiaMateria);
            }
            em.remove(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrera> findCarreraEntities() {
        return findCarreraEntities(true, -1, -1);
    }

    public List<Carrera> findCarreraEntities(int maxResults, int firstResult) {
        return findCarreraEntities(false, maxResults, firstResult);
    }

    private List<Carrera> findCarreraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrera.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Carrera findCarrera(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
