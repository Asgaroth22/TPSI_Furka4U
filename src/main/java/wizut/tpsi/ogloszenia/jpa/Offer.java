package wizut.tpsi.ogloszenia.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Pole obowiązkowe")
    @Size(max = 255, min = 5, message = "Tytuł musi mieć od {min} do {max} znaków długości")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Pole obowiązkowe")
    @Min(value=1900, message="Rocznik nie może być starszy niż 1900r.")
    @Column(name = "year")
    private Integer year;

    @NotNull(message = "Pole obowiązkowe")
    @Min(value=0,message="Przebieg nie może być mniejszy niż 0")
    @Column(name = "mileage")
    private Integer mileage;

    @NotNull(message = "Pole obowiązkowe")
    @Min(value=0, message = "Pojemność nie może być mniejsza niż 0")
    @Max(value=3, message = "Pojemność nie może być większa niż 3.0")
    @Column(name = "engine_size")
    private BigDecimal engineSize;

    @NotNull(message = "Pole obowiązkowe")
    @Min(value=0, message="Moc nie może być mniejsza niż 0")
    @Column(name = "engine_power")
    private Integer enginePower;

    @NotNull(message = "Pole obowiązkowe")
    @Min(value=1, message="Drzwi nie może być mniej niż 1")
    @Max(value=5, message="Drzwi nie może być więcej niż 5")
    @Column(name = "doors")
    private Integer doors;

    @NotNull(message = "Pole obowiązkowe")
    @Size(max = 30, min = 3, message = "Nazwa koloru musi być długości od 3 do 30 znaków")
    @Column(name = "colour")
    private String colour;

    @NotNull(message = "Pole obowiązkowe")
    @Lob
    @Size(max = 65535, min = 5, message = "Opis musi być długości od 5 do 65535 znaków")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Pole obowiązkowe")
    @Min(value=0, message="Cena nie może być ujemna")
    @Column(name = "price")
    private Integer price;

    @NotNull(message = "Pole obowiązkowe")
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @ManyToOne
    private CarModel model;

    @NotNull(message = "Pole obowiązkowe")
    @JoinColumn(name = "body_style_id", referencedColumnName = "id")
    @ManyToOne
    private BodyStyle bodyStyle;

    @NotNull(message = "Pole obowiązkowe")
    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    @ManyToOne
    private FuelType fuelType;
    
    @NotNull()
    private LocalDate dateAdded;

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(BigDecimal engineSize) {
        this.engineSize = engineSize;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
