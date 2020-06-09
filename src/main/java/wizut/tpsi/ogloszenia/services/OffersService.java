package wizut.tpsi.ogloszenia.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.jpa.OfferFilter;

@Service
@Transactional
public class OffersService {

    @PersistenceContext
    private EntityManager em;

    public CarManufacturer getCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }

    public CarModel getCarModel(int id) {   ///nie działa!!!
        return em.find(CarModel.class, id);
    }

    public List<CarManufacturer> getCarManufacturers() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels() {
        String jpql = "select cm from CarModel cm order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels(int manufacturerId) {
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);
        return query.getResultList();
    }

    public List<BodyStyle> getBodyStyles() {
        String jpql = "select cm from BodyStyle cm order by cm.name";
        TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }

    public List<FuelType> getFuelTypes() {
        String jpql = "select cm from FuelType cm order by cm.name";
        TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }

    public Offer getOffer(int offerId) {
        return em.find(Offer.class, offerId);
    }

    public List<Offer> getOffers() {
        String jpql = "select cm from Offer cm order by cm.title";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffers(OfferFilter offerFilter) {
        String jpql = "select cm from Offer cm";
        boolean filter = false;
        if (offerFilter.getManufacturerId() != null) {
            if (offerFilter.getModelId() != null) {
                jpql += " where cm.model.id = :id";
            } else {
                jpql += " where cm.model.manufacturer.id = :id";
            }
            filter = true;
        }
        if (offerFilter.getFuelTypeId() != null) {
            if (filter) {
                jpql += " and cm.fuelType.id = :fuelTypeId";
            } else {
                jpql += " where cm.fuelType.id = :fuelTypeId";
            }
            filter = true;
        }
        if (offerFilter.getYearMin() != null) {
            if (filter) {
                jpql += " and cm.year >= :yearMin";
            } else {
                jpql += " where cm.year >= :yearMin";
            }
            filter = true;
        }
        if (offerFilter.getYearMax() != null) {
            if (filter) {
                jpql += " and cm.year <= :yearMax";
            } else {
                jpql += " where cm.year <= :yearMax";
            }
            filter = true;
        }
        jpql += " order by cm.title";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        if (offerFilter.getManufacturerId() != null) {
            if (offerFilter.getModelId() != null) {
                query.setParameter("id", offerFilter.getModelId());
            } else {
                query.setParameter("id", offerFilter.getManufacturerId());
            }
        }
        if (offerFilter.getFuelTypeId() != null) {
            query.setParameter("fuelTypeId", offerFilter.getFuelTypeId());
        }
        if (offerFilter.getYearMin() != null) {
            query.setParameter("yearMin", offerFilter.getYearMin());
        }
        if (offerFilter.getYearMax() != null) {
            query.setParameter("yearMax", offerFilter.getYearMax());
        }
        List<Offer> result = query.getResultList();
        return result;
    }
    
    
    public Long getOffersCount(OfferFilter offerFilter) {
        String jpql = "select count(cm) from Offer cm";
        boolean filter = false;
        if (offerFilter.getManufacturerId() != null) {
            if (offerFilter.getModelId() != null) {
                jpql += " where cm.model.id = :id";
            } else {
                jpql += " where cm.model.manufacturer.id = :id";
            }
            filter = true;
        }
        if (offerFilter.getFuelTypeId() != null) {
            if (filter) {
                jpql += " and cm.fuelType.id = :fuelTypeId";
            } else {
                jpql += " where cm.fuelType.id = :fuelTypeId";
            }
            filter = true;
        }
        if (offerFilter.getYearMin() != null) {
            if (filter) {
                jpql += " and cm.year >= :yearMin";
            } else {
                jpql += " where cm.year >= :yearMin";
            }
            filter = true;
        }
        if (offerFilter.getYearMax() != null) {
            if (filter) {
                jpql += " and cm.year <= :yearMax";
            } else {
                jpql += " where cm.year <= :yearMax";
            }
            filter = true;
        }
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        if (offerFilter.getManufacturerId() != null) {
            if (offerFilter.getModelId() != null) {
                query.setParameter("id", offerFilter.getModelId());
            } else {
                query.setParameter("id", offerFilter.getManufacturerId());
            }
        }
        if (offerFilter.getFuelTypeId() != null) {
            query.setParameter("fuelTypeId", offerFilter.getFuelTypeId());
        }
        if (offerFilter.getYearMin() != null) {
            query.setParameter("yearMin", offerFilter.getYearMin());
        }
        if (offerFilter.getYearMax() != null) {
            query.setParameter("yearMax", offerFilter.getYearMax());
        }
        Long result = query.getSingleResult();
        return result;
    }
    
    
    public Offer createOffer(Offer offer) {
        em.persist(offer);
        return offer;
    }

    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select cm from Offer cm where cm.model.id = :id order by cm.title";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);
        return query.getResultList();
    }

    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        String jpql = "select cm from Offer cm where cm.model.manufacturer.id = :id order by cm.title";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);
        return query.getResultList();
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = em.find(Offer.class, id);
        em.remove(offer);
        return offer;
    }
    
    public Offer saveOffer(Offer offer) {
    return em.merge(offer);
}
}
