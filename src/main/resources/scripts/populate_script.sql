insert into PUBLIC.COLOR (ID, VALUE)
values  (01, 'ff6666'),
        (02, 'ff8c66'),
        (03, 'ffb366'),
        (04, 'ffd966'),
        (05, 'ffff66'),
        (06, 'd9ff66'),
        (07, 'b3ff66'),
        (08, '8cff66'),
        (09, '66ff66'),
        (10, '66ff8c'),
        (11, '66ffb3'),
        (12, '66ffd9'),
        (13, '66ffff'),
        (14, '66d9ff'),
        (15, '66b3ff'),
        (16, '668cff'),
        (17, '6666ff'),
        (18, '8c66ff'),
        (19, 'b366ff'),
        (20, 'd966ff'),
        (21, 'ff66ff'),
        (22, 'ff66d9'),
        (23, 'ff66b3'),
        (24, 'ff668c'),
        (25, 'ff6666');
insert into PUBLIC.CATEGORY (NAME, ID_COLOR)
values  ('Bar & locali', 01),
        ('Ristoranti & Fast-Food', 01),
        ('Spesa', 01),
        ('Abbigliamento & Scarpe', 13),
        ('Animali domestici', 13),
        ('Bellezza & Benessere', 13),
        ('Cancelleria & Utensili', 13),
        ('Casa & Giardino', 13),
        ('Elettronica', 13),
        ('Farmacia', 13),
        ('Figli', 13),
        ('Gioielli & Accessori', 13),
        ('Regali', 13),
        ('Tempo libero', 13),
        ('Taxi', 20),
        ('Trasporto pubblico', 20),
        ('Viaggi', 20),
        ('Assicurazione veicoli', 19),
        ('Carburante', 19),
        ('Leasing', 19),
        ('Manutenzione veicoli', 19),
        ('Noleggi', 19),
        ('Parcheggio', 19),
        ('Alcolici & Tabacco', 09),
        ('Cultura & Eventi sportivi', 09),
        ('Donazioni', 09),
        ('Formazione & Sviluppo', 09),
        ('Hobby', 09),
        ('Libri', 09),
        ('Lotterie & Giochi d''azzardo', 09),
        ('Salute', 09),
        ('Sport & Fitness', 09),
        ('TV & Streaming', 09),
        ('Hotel & Alberghi', 16),
        ('Internet', 16),
        ('Servizi postali', 16),
        ('Software, App & Giochi', 16),
        ('Telefono', 16),
        ('Assegno familiare', 11),
        ('Assicurazioni', 11),
        ('Consulenza', 11),
        ('Multe', 11),
        ('Prestiti', 11),
        ('Tasse', 11),
        ('Investimenti finanziari', 24),
        ('Riscossioni', 24),
        ('Buoni & Coupon', 04),
        ('Dividendi', 04),
        ('Quote & Contributi', 04),
        ('Rimborsi', 04),
        ('Stipendio', 04),
        ('Vendite', 04),
        ('Altro', 17);
insert into PUBLIC.RELATIONSHIP (ID, NAME)
values  (01, 'padre'),
        (02, 'madre'),
        (03, 'marito'),
        (04, 'moglie'),
        (05, 'sorella'),
        (06, 'fratello'),
        (07, 'nonno'),
        (08, 'nonna'),
        (09, 'zio'),
        (10, 'zia'),
        (11, 'cugino'),
        (12, 'cugina'),
        (13, 'figlio'),
        (14, 'figlia'),
        (15, 'amico'),
        (16, 'amica'),
        (17, 'fidanzato'),
        (18, 'fidanzata');