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
INSERT INTO `employee` (`id`, `login`, `firstname`, `lastname`, `password`, `mail`, `phone`, `birth`, `role`) VALUES
  ('usr-023-001', 'luffy', 'Luffy', 'Monkey D', '12345678', 'luffy@gmail.com', '6 90 18 53 35', '2005-05-13', 1),
  ('usr-024-002', 'perona', 'Sama', 'Perona', 'Aqszaqsz0', 'perona@queen.com', '6 90 28 47 16', '2003-11-04', 1);

  
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
  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-001', 'Brondon', 'NJOTSA', '6 90 18 53 35', 'brondonnjotsa@gmail.com', '1'),
  ('cst-024-002', 'Makam Audrey', 'KAMMEGNE', '6 98 50 65 78', 'makam@gmail.com', '1'),
  ('cst-024-003', 'Nick Larson', 'PIEBIENG', '6 46 65 48 64', 'nick@gmail.com', '1'),
  ('cst-024-004', 'Mamiafo', 'Gnitedemg', '6 54 64 40 50', 'mamiafo@gmail.com', '1');

  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-005', 'Elodie Erin', 'BABAN', '6 94 054 65 08', 'elodie@gmail.com', '1'),
  ('cst-024-006', 'D Luffy ', 'MONKEY', '6 87 04 54 04', 'luffy@gmail.com', '1'),
  ('cst-024-007', 'Zorro', 'RORONOA', '6 87 50 65 48', 'zorro@gmail.com', '1');

  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-008', 'Sanji', 'VINSMOOKE', '6 50 45 04 65', 'sanji@gmail.com', '1'),
  ('cst-024-009', 'Linlin', 'CHARLOTTE', '6 80 65 68 40', 'linlin@gmail.com', '1'),
  ('cst-024-010', 'Katakuri', 'CHARLOTTE', '6 80 98 50 07', 'katakuri@gmail.com', '1');

  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-011', 'D Roger', 'GOLD', '6 80 65 04 80', 'roger@gmail.com', '1'),
  ('cst-024-012', 'Rayleight', 'SYLVER', '6 80 50 65 87', 'rayleight@gmail.com', '1'),
  ('cst-024-013', 'Gaban', 'SCOPPER', '6 90 80 48 98', 'gaban@gmail.com', '1');

  CREATE TABLE `product`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`name`		    VARCHAR(100) NOT NULL,
  	`brand`         VARCHAR(100) NOT NULL,
  	`barcode` 		VARCHAR(100) NOT NULL,
  	`category` 		VARCHAR(100) NOT NULL,
  	`description` 	TEXT NOT NULL,
  	`base_price` 	int NOT NULL,
  	`min_price` 	int NOT NULL,
  	`minimum` 	int NOT NULL,
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
  	`product` 		        VARCHAR(20) NOT NULL,
  	`quantity`		    VARCHAR(20),
  	`price` 		    int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (invoice) REFERENCES invoice(id),
  	FOREIGN KEY (product) REFERENCES product(id)
  );



  INSERT INTO `product`(`id`, `name`, `brand`, `barcode`, `category`, `description`, `base_price`, `min_price`, `minimum`) VALUES
  ('pro-024-001', "Clé à molette ajustable", "ToolMaster", '1234567890123', 'OUTILS', "Clé à molette de qualité professionnelle avec mâchoires en acier trempé.", 5000, 4000, 5),
  ('pro-024-002', "Tournevis Phillips", "CraftPro", '1234567890130', 'OUTILS', "Tournevis à tête Phillips pour les travaux de vissage précis.", 3000, 2500, 10),
  ('pro-024-003', "Cadenas en laiton", "SecureLock", '1234567890147', 'SÉCURITÉ', "Cadenas solide en laiton pour sécuriser vos biens personnels.", 4000, 3500, 15),
  ('pro-024-004', "Serrure de porte", "HomeSafe", '1234567890154', 'SÉCURITÉ', "Serrure de porte résistante avec clés assorties.", 6000, 5000, 8),
  ('pro-024-005', "Ruban à mesurer", "MeasureMaster", '1234567890161', 'OUTILS', "Ruban à mesurer de 5 mètres pour des mesures précises.", 2000, 1500, 20),
  ('pro-024-006', "Clous galvanisés", "BuildRight", '1234567890178', 'MATÉRIAUX', "Clous galvanisés de qualité pour la construction.", 3000, 2500, 50),
  ('pro-024-007', "Vis autotaraudeuses", "ScrewTech", '1234567890185', 'MATÉRIAUX', "Vis autotaraudeuses en acier inoxydable pour une fixation solide.", 3500, 3000, 30),
  ('pro-024-008', "Poignée de porte", "DoorCraft", '1234567890192', 'QUINCAILLERIE', "Poignée de porte moderne en acier inoxydable.", 4500, 4000, 12),
  ('pro-024-009', "Charnières de porte", "HingeMaster", '1234567890208', 'QUINCAILLERIE', "Charnières de porte robustes en acier pour une installation durable.", 3500, 3000, 15),
  ('pro-024-010', "Équerre de menuisier", "WoodCraft", '1234567890215', 'OUTILS', "Équerre de menuisier en aluminium pour des angles précis.", 2500, 2000, 10),
  ('pro-024-011', "Marteau de charpentier", "HammerPro", '1234567890222', 'OUTILS', "Marteau de charpentier avec manche en fibre de verre pour une prise en main confortable.", 4000, 3500, 8),
  ('pro-024-012', "Colle à bois", "BondWood", '1234567890239', 'MATÉRIAUX', "Colle à bois professionnelle pour des joints solides.", 1500, 1200, 20),
  ('pro-024-013', "Pince coupante", "CutMaster", '1234567890246', 'OUTILS', "Pince coupante robuste pour la coupe de fils et de câbles.", 3500, 3000, 15),
  ('pro-024-014', "Écrous et boulons assortis", "Nut&Bolt", '1234567890253', 'MATÉRIAUX', "Kit d'écrous et de boulons de différentes tailles pour divers projets.", 4000, 3500, 25),
  ('pro-024-015', "Aimant de porte", "MagnetTech", '1234567890260', 'QUINCAILLERIE', "Aimant de porte puissant pour maintenir les portes fermées.", 2500, 2000, 10),
  ('pro-024-016', "Étagère d'angle", "CornerCraft", '1234567890277', 'QUINCAILLERIE', "Étagère d'angle en acier pour maximiser l'espace de rangement.", 6000, 5000, 8),
  ('pro-024-017', "Gonds de porte", "HingeTech", '1234567890284', 'QUINCAILLERIE', "Gonds de porte résistants en acier inoxydable.", 3000, 2500, 15),
  ('pro-024-018', "Rouleau de ruban adhésif", "AdhesivePro", '1234567890291', 'QUINCAILLERIE', "Rouleau de ruban adhésif multi-usage.", 1000, 800, 20),
  ('pro-024-019', "Niveau à bulle", "BubbleLevel", '1234567890307', 'OUTILS', "Niveau à bulle précis pour les travaux de construction.", 2000, 1500, 10),
  ('pro-024-020', "Ciseaux de couture", "SewingCraft", '1234567890314', 'COUTURE', "Ciseaux de couture de qualité pour les projets de couture.", 1500, 1200, 15),
  ('pro-024-021', "Aiguilles à coudre assorties", "NeedleCraft", '1234567890321', 'COUTURE', "Aiguilles à coudre assorties pour différents types de tissus.", 1000, 800, 30),
  ('pro-024-022', "Fermetures à glissière", "ZipperTech", '1234567890338', 'COUTURE', "Fermetures à glissière de différentes longueurs et couleurs.", 2000, 1500, 20),
  ('pro-024-023', "Bobines de fil à coudre", "ThreadMaster", '1234567890345', 'COUTURE', "Bobines de fil à coudre de différentes couleurs.", 1500, 1200, 25),
  ('pro-024-024', "Ciseaux à tissu", "FabricCut", '1234567890352', 'COUTURE', "Ciseaux à tissu tranchants pour la coupe précise de tissus.", 2000, 1500, 15),
  ('pro-024-025', "Aiguilles à tricoter", "KnitCraft", '1234567890369', 'COUTURE', "Aiguilles à tricoter de différentes tailles pour les projets de tricot.", 1000, 800, 20),
  ('pro-024-026', "Ruban à mesurer de couture", "SewingMeasure", '1234567890376', 'COUTURE', "Ruban à mesurer de couture pour des mesures précises.", 1500, 1200, 15),
  ('pro-024-027', "Aiguilles à broder", "EmbroideryNeedle", '1234567890383', 'COUTURE', "Aiguilles à broder assorties pour la broderie à la main.", 1000, 800, 25),
  ('pro-024-028', "Fermeture à glissière invisible", "InvisibleZipper", '1234567890390', 'COUTURE', "Fermeture à glissière invisible pour des finitions soignées.", 2000, 1500, 10),
  ('pro-024-029', "Boutons assortis", "ButtonCraft", '1234567890406', 'COUTURE', "Boutons assortis pour la couture et la décoration.", 1000, 800, 30),
  ('pro-024-030', "Ruban élastique", "ElasticBand", '1234567890413', 'COUTURE', "Ruban élastique pour la couture de vêtements et de projets d'artisanat.", 1500, 1200, 20),
  ('pro-024-031', "Épingles de sûreté", "SafetyPin", '1234567890420', 'COUTURE', "Épingles de sûreté pour la couture et la fixation temporaires.", 1000, 800, 30),
  ('pro-024-032', "Ciseaux à broder", "EmbroideryScissors", '1234567890437', 'COUTURE', "Ciseaux à broder avec pointe fine pour les détails délicats.", 2000, 1500, 15),
  ('pro-024-033', "Fermeture éclair séparable", "SeparatingZipper", '1234567890444', 'COUTURE', "Fermeture éclair séparable pour les vestes et les sacs.", 2000, 1500, 10),
  ('pro-024-034', "Boutons-pression", "SnapFastener", '1234567890451', 'COUTURE', "Boutons-pression pour la couture rapide et facile.", 1000, 800, 25),
  ('pro-024-035', "Ruban adhésif double face", "DoubleSidedTape", '1234567890468', 'QUINCAILLERIE', "Ruban adhésif double face pour une fixation solide.", 1500, 1200, 20),
  ('pro-024-036', "Mètre ruban rétractable", "RetractableMeasure", '1234567890475', 'OUTILS', "Mètre ruban rétractable pour des mesures précises.", 2000, 1500, 15),
  ('pro-024-037', "Ciseaux de cuisine", "KitchenScissors", '1234567890482', 'CUISSON', "Ciseaux de cuisine polyvalents pour la découpe d'aliments.", 1500, 1200, 20),
  ('pro-024-038', "Planche à découper", "CuttingBoard", '1234567890499', 'CUISSON', "Planche à découper en plastique résistant pour la préparation des repas.", 2000, 1500, 15),
  ('pro-024-039', "Louche en acier inoxydable", "StainlessLadle", '1234567890505', 'CUISSON', "Louche en acier inoxydable pour servir les soupes et les sauces.", 2500, 2000, 10),
  ('pro-024-040', "Écumoire en nylon", "NylonSkimmer", '1234567890512', 'CUISSON', "Écumoire en nylon pour égoutter les aliments frits.", 1500, 1200, 15),
  ('pro-024-041', "Cuillère à fentes", "SlottedSpoon", '1234567890529', 'CUISSON', "Cuillère à fentes pour égoutter les aliments tout en les servant.", 1000, 800, 20),
  ('pro-024-042', "Passoire en acier inoxydable", "StainlessStrainer", '1234567890536', 'CUISSON', "Passoire en acier inoxydable pour égoutter les pâtes et les légumes.", 2500, 2000, 10),
  ('pro-024-043', "Pince de cuisine", "KitchenTongs", '1234567890543', 'CUISSON', "Pince de cuisine en acier inoxydable pour retourner les aliments.", 2000, 1500, 15),
  ('pro-024-044', "Rouleau à pâtisserie", "PastryRoller", '1234567890550', 'CUISSON', "Rouleau à pâtisserie en bois pour étaler la pâte.", 1500, 1200, 20),
  ('pro-024-045', "Moule à gâteau", "CakeMold", '1234567890567', 'CUISSON', "Moule à gâteau antiadhésif pour des gâteaux parfaitement formés.", 2500, 2000, 10),
  ('pro-024-046', "Fouet de cuisine", "WhiskMaster", '1234567890574', 'CUISSON', "Fouet de cuisine en acier inoxydable pour battre les œufs et la crème.", 1000, 800, 25),
  ('pro-024-047', "Plat de cuisson en verre", "GlassBakingDish", '1234567890581', 'CUISSON', "Plat de cuisson en verre résistant à la chaleur pour les casseroles et les rôtis.", 3000, 2500, 8),
  ('pro-024-048', "Moule à muffins", "MuffinPan", '1234567890598', 'CUISSON', "Moule à muffins antiadhésif pour des muffins moelleux.", 2000, 1500, 12),
  ('pro-024-049', "Spatule en silicone", "SiliconeSpatula", '1234567890604', 'CUISSON', "Spatule en silicone flexible pour retourner les aliments sans les rayer.", 1500, 1200, 15),
  ('pro-024-050', "Tire-bouchon", "CorkscrewMaster", '1234567890611', 'CUISSON', "Tire-bouchon classique avec manche en bois.", 2000, 1500, 10);


INSERT INTO `supplier`(`id`, `firstname`, `lastname`, `description`, `phone`, `mail`) VALUES
('sup-024-001', 'Makam', 'Nguemo', 'Fournisseur local de quincaillerie offrant une large gamme de produits à des prix compétitifs.', '6 75 23 45 67', 'makam@example.com'),
('sup-024-002', 'Tchakounté', 'Kamga', 'Fournisseur spécialisé dans les matériaux de construction et les outils de qualité.', '6 77 34 56 89', 'tchakounte@example.com'),
('sup-024-003', 'Nafissatou', 'Nji', 'Fournisseur de fournitures de couture proposant des produits variés pour les artisans.', '6 78 45 23 67', 'nafissatou@example.com'),
('sup-024-004', 'Mbonjo', 'Tchokote', 'Fournisseur local de quincaillerie avec une bonne réputation pour sa fiabilité et son service client.', '6 79 56 34 78', 'mbonjo@example.com'),
('sup-024-005', 'Atangana', 'Nganou', 'Fournisseur de produits de cuisine offrant une large sélection d\'ustensiles et d\'accessoires.', '6 75 34 78 90', 'atangana@example.com'),
('sup-024-006', 'Nkoumou', 'Essomba', 'Fournisseur de matériaux de couture avec des produits de qualité et des prix abordables.', '6 76 45 67 89', 'nkoumou@example.com'),
('sup-024-007', 'Ngo', 'Ngassa', 'Fournisseur de quincaillerie offrant une variété de produits pour les besoins domestiques et industriels.', '6 77 56 78 90', 'ngo@example.com'),
('sup-024-008', 'Tchamba', 'Fotsing', 'Fournisseur de produits de jardinage proposant des outils et des équipements de qualité.', '6 78 67 89 01', 'tchamba@example.com'),
('sup-024-009', 'Mballa', 'Mvogo', 'Fournisseur de peintures et de produits de décoration pour les projets résidentiels et commerciaux.', '6 75 78 90 12', 'mballa@example.com'),
('sup-024-010', 'Njoh', 'Mandeng', 'Fournisseur de matériaux de construction offrant une large gamme de produits pour les projets de construction.', '6 76 89 01 23', 'njoh@example.com'),
('sup-024-011', 'Fouda', 'Mbia', 'Fournisseur de produits de nettoyage proposant des solutions efficaces pour les ménages et les entreprises.', '6 77 90 12 34', 'fouda@example.com'),
('sup-024-012', 'Toukam', 'Takougang', 'Fournisseur de fournitures électriques offrant une sélection complète de produits pour les installations électriques.', '6 78 01 23 45', 'toukam@example.com'),
('sup-024-013', 'Elong', 'Ngassa', 'Fournisseur de matériaux de plomberie offrant des produits de qualité pour les projets de plomberie résidentiels et commerciaux.', '6 75 12 34 56', 'elong@example.com');

INSERT INTO `lot`(`id`, `product`, `num_lot`, `quantity`, `variant`, `delivery_date`, `expiration_date`, `supplier`, `price`, `status`) VALUES
('lot-024-001', 'pro-024-001', 'LOT123001', 75, 'Rouge', '2024-04-16', '2025-04-16', 'sup-024-011', 2500, 1),
('lot-024-002', 'pro-024-002', 'LOT456002', 80, 'Petite taille', '2024-04-16', '2025-04-16', 'sup-024-012', 2800, 1),
('lot-024-003', 'pro-024-003', 'LOT789003', 85, 'Type D', '2024-04-16', '2025-04-16', 'sup-024-001', 3000, 1),
('lot-024-004', 'pro-024-004', 'LOT111004', 90, 'Taille moyenne', '2024-04-16', '2025-04-16', 'sup-024-002', 3200, 1),
('lot-024-005', 'pro-024-005', 'LOT222005', 95, 'Vert', '2024-04-16', '2025-04-16', 'sup-024-003', 3500, 1),
('lot-024-006', 'pro-024-006', 'LOT333006', 100, 'Grand modèle', '2024-04-16', '2025-04-16', 'sup-024-004', 3800, 1),
('lot-024-007', 'pro-024-007', 'LOT444007', 105, 'Modèle Z', '2024-04-16', '2025-04-16', 'sup-024-005', 4000, 1),
('lot-024-008', 'pro-024-008', 'LOT555008', 110, 'Taille XL', '2024-04-16', '2025-04-16', 'sup-024-006', 4200, 1),
('lot-024-009', 'pro-024-009', 'LOT666009', 115, 'Type E', '2024-04-16', '2025-04-16', 'sup-024-007', 4500, 1),
('lot-024-010', 'pro-024-010', 'LOT777010', 120, 'Classique', '2024-04-16', '2025-04-16', 'sup-024-008', 4800, 1),
('lot-024-011', 'pro-024-011', 'LOT888011', 125, 'Taille 8 pouces', '2024-04-16', '2025-04-16', 'sup-024-009', 5100, 1),
('lot-024-012', 'pro-024-012', 'LOT999012', 130, 'Couleur bleu', '2024-04-16', '2025-04-16', 'sup-024-010', 5400, 1),
('lot-024-013', 'pro-024-013', 'LOT101013', 135, 'Type A', '2024-04-16', '2025-04-16', 'sup-024-011', 5700, 1),
('lot-024-014', 'pro-024-014', 'LOT111014', 140, 'Modèle X', '2024-04-16', '2025-04-16', 'sup-024-012', 6000, 1),
('lot-024-015', 'pro-024-015', 'LOT121015', 145, 'Taille moyenne', '2024-04-16', '2025-04-16', 'sup-024-013', 6300, 1),
('lot-024-016', 'pro-024-016', 'LOT131016', 150, 'Type B', '2024-04-16', '2025-04-16', 'sup-024-004', 6600, 1),
('lot-024-017', 'pro-024-017', 'LOT141017', 155, 'Modèle Y', '2024-04-16', '2025-04-16', 'sup-024-010', 6900, 1),
('lot-024-018', 'pro-024-018', 'LOT151018', 160, 'Taille grande', '2024-04-16', '2025-04-16', 'sup-024-006', 7200, 1),
('lot-024-019', 'pro-024-019', 'LOT161019', 165, 'Type C', '2024-04-16', '2025-04-16', 'sup-024-007', 7500, 1),
('lot-024-020', 'pro-024-020', 'LOT171020', 170, '', '2024-04-16', '2025-04-16', 'sup-024-008', 7800, 1),
('lot-024-021', 'pro-024-021', 'LOT181021', 175, 'Couleur jaune', '2024-04-16', '2025-04-16', 'sup-024-009', 8100, 1),
('lot-024-022', 'pro-024-022', 'LOT191022', 180, 'Grande taille', '2024-04-16', '2025-04-16', 'sup-024-003', 8400, 1),
('lot-024-023', 'pro-024-023', 'LOT201023', 185, 'Type D', '2024-04-16', '2025-04-16', 'sup-024-001', 8700, 1),
('lot-024-024', 'pro-024-024', 'LOT211024', 190, 'Modèle Z', '2024-04-16', '2025-04-16', 'sup-024-002', 9000, 1),
('lot-024-025', 'pro-024-025', 'LOT221025', 195, 'Taille XL', '2024-04-16', '2025-04-16', 'sup-024-003', 9300, 1),
('lot-024-026', 'pro-024-026', 'LOT231026', 200, 'Type E', '2024-04-16', '2025-04-16', 'sup-024-004', 9600, 1),
('lot-024-027', 'pro-024-027', 'LOT241027', 205, 'Classique', '2024-04-16', '2025-04-16', 'sup-024-005', 9900, 1),
('lot-024-028', 'pro-024-028', 'LOT251028', 210, 'Taille 8 pouces', '2024-04-16', '2025-04-16', 'sup-024-006', 10200, 1),
('lot-024-029', 'pro-024-029', 'LOT261029', 215, 'Couleur bleu', '2024-04-16', '2025-04-16', 'sup-024-007', 10500, 1),
('lot-024-030', 'pro-024-030', 'LOT271030', 220, 'Type A', '2024-04-16', '2025-04-16', 'sup-024-008', 10800, 1),
('lot-024-031', 'pro-024-031', 'LOT281031', 225, 'Modèle X', '2024-04-16', '2025-04-16', 'sup-024-009', 11100, 1),
('lot-024-032', 'pro-024-032', 'LOT291032', 230, 'Taille moyenne', '2024-04-16', '2025-04-16', 'sup-024-005', 11400, 1),
('lot-024-033', 'pro-024-033', 'LOT301033', 235, 'Type B', '2024-04-16', '2025-04-16', 'sup-024-001', 11700, 1),
('lot-024-034', 'pro-024-034', 'LOT311034', 240, 'Modèle Y', '2024-04-16', '2025-04-16', 'sup-024-002', 12000, 1),
('lot-024-035', 'pro-024-035', 'LOT321035', 245, 'Taille grande', '2024-04-16', '2025-04-16', 'sup-024-003', 12300, 1),
('lot-024-036', 'pro-024-036', 'LOT331036', 250, 'Type C', '2024-04-16', '2025-04-16', 'sup-024-004', 12600, 1),
('lot-024-037', 'pro-024-037', 'LOT341037', 255, '', '2024-04-16', '2025-04-16', 'sup-024-005', 12900, 1),
('lot-024-038', 'pro-024-038', 'LOT351038', 260, 'Couleur jaune', '2024-04-16', '2025-04-16', 'sup-024-006', 13200, 1),
('lot-024-039', 'pro-024-039', 'LOT361039', 265, 'Grande taille', '2024-04-16', '2025-04-16', 'sup-024-007', 13500, 1),
('lot-024-040', 'pro-024-040', 'LOT371040', 270, 'Type D', '2024-04-16', '2025-04-16', 'sup-024-008', 13800, 1),
('lot-024-041', 'pro-024-041', 'LOT381041', 275, 'Modèle Z', '2024-04-16', '2025-04-16', 'sup-024-009', 14100, 1),
('lot-024-042', 'pro-024-042', 'LOT391042', 280, 'Taille XL', '2024-04-16', '2025-04-16', 'sup-024-005', 14400, 1),
('lot-024-043', 'pro-024-043', 'LOT401043', 285, 'Type E', '2024-04-16', '2025-04-16', 'sup-024-001', 14700, 1),
('lot-024-044', 'pro-024-044', 'LOT411044', 290, 'Classique', '2024-04-16', '2025-04-16', 'sup-024-002', 15000, 1),
('lot-024-045', 'pro-024-045', 'LOT421045', 295, 'Taille 8 pouces', '2024-04-16', '2025-04-16', 'sup-024-003', 15300, 1),
('lot-024-046', 'pro-024-046', 'LOT431046', 300, 'Couleur bleu', '2024-04-16', '2025-04-16', 'sup-024-004', 15600, 1),
('lot-024-047', 'pro-024-047', 'LOT441047', 305, 'Type A', '2024-04-16', '2025-04-16', 'sup-024-005', 15900, 1),
('lot-024-048', 'pro-024-048', 'LOT451048', 310, 'Modèle X', '2024-04-16', '2025-04-16', 'sup-024-006', 16200, 1),
('lot-024-049', 'pro-024-049', 'LOT461049', 315, 'Taille moyenne', '2024-04-16', '2025-04-16', 'sup-024-007', 16500, 1),
('lot-024-050', 'pro-024-050', 'LOT471050', 320, 'Type B', '2024-04-16', '2025-04-16', 'sup-024-008', 16800, 1),
('lot-024-051', 'pro-024-001', 'LOT123051', 90, 'Bleu', '2024-04-16', '2025-04-16', 'sup-024-011', 3200, 1),
('lot-024-052', 'pro-024-002', 'LOT456052', 95, 'Moyenne taille', '2024-04-16', '2025-04-16', 'sup-024-012', 3500, 1),
('lot-024-053', 'pro-024-003', 'LOT789053', 100, 'Type F', '2024-04-16', '2025-04-16', 'sup-024-013', 3800, 1),
('lot-024-054', 'pro-024-004', 'LOT111054', 105, 'Modèle Y', '2024-04-16', '2025-04-16', 'sup-024-004', 4000, 1),
('lot-024-055', 'pro-024-005', 'LOT222055', 110, 'Vert clair', '2024-04-16', '2025-04-16', 'sup-024-005', 4200, 1),
('lot-024-056', 'pro-024-006', 'LOT333056', 115, 'Petit modèle', '2024-04-16', '2025-04-16', 'sup-024-006', 4500, 1),
('lot-024-057', 'pro-024-007', 'LOT444057', 120, 'Type G', '2024-04-16', '2025-04-16', 'sup-024-007', 4800, 1),
('lot-024-058', 'pro-024-008', 'LOT555058', 125, 'Taille XXL', '2024-04-16', '2025-04-16', 'sup-024-008', 5100, 1),
('lot-024-059', 'pro-024-009', 'LOT666059', 130, 'Type H', '2024-04-16', '2025-04-16', 'sup-024-009', 5400, 1),
('lot-024-060', 'pro-024-010', 'LOT777060', 135, 'Couleur orange', '2024-04-16', '2025-04-16', 'sup-024-005', 5700, 1);
