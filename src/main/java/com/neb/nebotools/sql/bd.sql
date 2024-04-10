CREATE TABLE `employee`(
	`id` 			VARCHAR(20) PRIMARY KEY,
	`login`			VARCHAR(100) NOT NULL,
	`firstname`		VARCHAR(100) NOT NULL,
	`lastname` 		VARCHAR(100) NOT NULL,
	`password` 		VARCHAR(100) NOT NULL,
	`mail` 			VARCHAR(100) NOT NULL,
	`phone` 		VARCHAR(15) NOT NULL,
	`birth` 		DATE NOT NULL,
	`sex` 		    int NOT NULL DEFAULT 1,
	`role` 			int NOT NULL DEFAULT 1,
	`is_actif`  	int NOT NULL DEFAULT 1
);

/* Table employee*/
INSERT INTO `employee` (`id`, `login`, `firstname`, `lastname`, `password`, `mail`, `phone`, `role`, `nb_person`) VALUES
  ('usr-023-001', 'luffy', 'Luffy', 'Monkey D', '12345678', 'luffy@gmail.com', '6 90 18 53 35', 1, 3),
  ('usr-024-002', 'perona', 'Sama', 'Perona', 'Aqszaqsz0', 'perona@queen.com', '6 90 28 47 16', 1, 1);

  
  CREATE TABLE `supplier`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`firstname`		VARCHAR(100) NOT NULL,
  	`lastname` 		VARCHAR(100) NOT NULL,
  	`description` 	TEXT NOT NULL,
  	`phone` 		VARCHAR(15) NOT NULL,
  	`mail` 		    VARCHAR(100),
  	`is_actif`  	int NOT NULL DEFAULT 1
  );

  CREATE TABLE `customer`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`firstname`		VARCHAR(100) NOT NULL,
  	`lastname` 		VARCHAR(100) NOT NULL,
  	`phone` 		VARCHAR(15) NOT NULL,
  	`mail` 		    VARCHAR(100),
  	`is_actif`  	int NOT NULL DEFAULT 1
  );

  CREATE TABLE `product`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`name`		    VARCHAR(100) NOT NULL,
  	`barcode` 		VARCHAR(100) NOT NULL,
  	`category` 		VARCHAR(100) NOT NULL,
  	`description` 	TEXT NOT NULL,
  	`base_price` 	int NOT NULL,
  	`min_price` 	int NOT NULL,
  	`is_actif`  	int NOT NULL DEFAULT 1
  );

  CREATE TABLE `lot`(
  	`id` 			    VARCHAR(20) PRIMARY KEY,
  	`product` 		    VARCHAR(20) NOT NULL,
  	`num_lot`		    VARCHAR(100) NOT NULL,
  	`quantity` 		    int NOT NULL,
  	`variant` 		    VARCHAR(20),
  	`delivery_date`     DATE NOT NULL,
  	`expiration_date` 	DATE,
  	`supplier` 		    VARCHAR(20) NOT NULL,
  	`price`     	    int NOT NULL,
  	`status` 	        int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (product) REFERENCES product(id),
  	FOREIGN KEY (supplier) REFERENCES supplier(id)
  );

  CREATE TABLE `invoice`(
  	`id` 			    VARCHAR(20) PRIMARY KEY,
  	`created_date` 		DATE DEFAULT CURRENT_TIMESTAMP,
  	`employee`		        VARCHAR(20) NOT NULL,
  	`customer`		    VARCHAR(20),
  	`price` 		    int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (employee) REFERENCES employee(id),
  	FOREIGN KEY (customer) REFERENCES customer(id)
  );

  CREATE TABLE `line_invoice`(
  	`id` 			    VARCHAR(20) PRIMARY KEY,
  	`invoice` 		    VARCHAR(20) NOT NULL,
  	`lot` 		        VARCHAR(20) NOT NULL,
  	`quantity`		    VARCHAR(20),
  	`price` 		    int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (invoice) REFERENCES invoice(id),
  	FOREIGN KEY (lot) REFERENCES lot(id)
  );

