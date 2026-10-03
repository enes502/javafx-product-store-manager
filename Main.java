package application;

abstract class Product {
    private String name;
    private double price;
    private static int count = 0;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String display();
}

class Book extends Product {
    private int numPages;

    public Book(String name, double price, int numPages) {
        super(name, price);
        this.numPages = numPages;
    }

    @Override
    public String display() {
        return ". Book - Name: " + getName() + ", Price: " + getPrice() + ", Number of Pages: " + numPages;
    }
}

class Electronic extends Product {
    private String brand;
    private boolean isSmart;

    public Electronic(String name, double price, String brand, boolean isSmart) {
        super(name, price);
        this.brand = brand;
        this.isSmart = isSmart;
    }

    @Override
    public String display() {
        return ". Electronic - Name: " + getName() + ", Price: " + getPrice() + ", Brand: " + brand + ", Is Smart: " + isSmart;
    }
}

interface ProductManager {
    void addProduct(Product product);
    String displayProducts();
}

class Store implements ProductManager {
    private Product[] products;
    private int numProducts;

    public Store() {
        products = new Product[10];
        numProducts = 0;
    }

    @Override
    public void addProduct(Product product) {
        if (numProducts < products.length) {
            products[numProducts] = product;
            numProducts++;
        } else {
            System.out.println("Store is full!");
        }
    }

    @Override
    public String displayProducts() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numProducts; i++) {
            result.append((i + 1) + products[i].display()).append("\n");
        }
        return result.toString();
    }
}
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Store store = new Store();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Product Management App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        Label nameLabel = new Label();

        TextField priceInput = new TextField();
        priceInput.setPromptText("Price");
        Label priceLabel = new Label();

        TextField numPagesInput = new TextField();
        numPagesInput.setPromptText("Number of Pages");
        Label numPagesLabel = new Label();

        TextField brandInput = new TextField();
        brandInput.setPromptText("Brand");
        Label brandLabel = new Label();

        ComboBox<String> isSmartComboBox = new ComboBox<>();
        isSmartComboBox.getItems().addAll("Yes", "No");
        isSmartComboBox.setPromptText("Is Smart");
        Label isSmartLabel = new Label();

        Button addBookButton = new Button("Add Book");
        Button addElectronicButton = new Button("Add Electronic");
        Button displayProductsButton = new Button("Display Products");

        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        addBookButton.setOnAction(event -> {
            String name = nameInput.getText();
            String priceText = priceInput.getText();
            String numPagesText = numPagesInput.getText();

            if (name.isEmpty() || priceText.isEmpty() || numPagesText.isEmpty()) {
                resultLabel.setText("All fields must be filled!");
                return;
            }
            try {
                double price = Double.parseDouble(priceText);
                try {
                    int numPagesNumber = Integer.parseInt(numPagesText);

                    if (numPagesNumber <= 0 || price < 0) {
                        resultLabel.setText("Price and number of pages must be positive.");
                    } else {
                        store.addProduct(new Book(name, price, numPagesNumber));
                        resultLabel.setText("Added book: " + name);
                    }
                } catch (NumberFormatException e) {
                    resultLabel.setText("Invalid input! Number of pages must be integer value.");
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input: Price must be number");
            }
        });

        addElectronicButton.setOnAction(event -> {
            String name = nameInput.getText();
            String priceText = priceInput.getText();
            String brand = brandInput.getText();
            String isSmartText = isSmartComboBox.getValue();

            if (name.isEmpty() || priceText.isEmpty() || brand.isEmpty() || isSmartText == null || isSmartText.isEmpty()) {
                resultLabel.setText("All fields must be filled!");
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                boolean isSmart = Boolean.parseBoolean(isSmartText);

                store.addProduct(new Electronic(name, price, brand, isSmart));
                resultLabel.setText("Added electronic: " + name);
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input! Price must be a number.");
            }
        });

        displayProductsButton.setOnAction(event -> {
            resultLabel.setText(store.displayProducts());
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(nameLabel, 2, 0);
        grid.add(new Label("Price:"), 0, 1);
        grid.add(priceInput, 1, 1);
        grid.add(priceLabel, 2, 1);
        grid.add(new Label("Number of Pages:"), 0, 2);
        grid.add(numPagesInput, 1, 2);
        grid.add(numPagesLabel, 2, 2);
        grid.add(new Label("Brand:"), 0, 3);
        grid.add(brandInput, 1, 3);
        grid.add(brandLabel, 2, 3);
        grid.add(new Label("Is Smart:"), 0, 4);
        grid.add(isSmartComboBox, 1, 4);
        grid.add(isSmartLabel, 2, 4);
        grid.add(addBookButton, 0, 5);
        grid.add(addElectronicButton, 1, 5);
        grid.add(displayProductsButton, 0, 6);
        grid.add(resultLabel, 0, 7, 2, 1);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
