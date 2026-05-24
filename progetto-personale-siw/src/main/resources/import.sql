-- Istruttori
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Esposito', 'Marco', 'istruttore di funzionale');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Rossi', 'Laura', 'istruttore di yoga');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Bianchi', 'Andrea', 'istruttore di crossfit');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Verdi', 'Sofia', 'istruttore di pilates');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Ferrari', 'Luca', 'istruttore di body building');

-- Corsi (associati agli istruttori tramite istruttore_id)
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Funzionale Avanzato', 'Allenamento completo per tutto il corpo con esercizi funzionali', 'Avanzato', 15, 60, 1);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Yoga Dolce', 'Lezioni di yoga per rilassamento e flessibilità', 'Principiante', 20, 75, 51);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'CrossFit Intenso', 'Allenamento ad alta intensità per tutto il corpo', 'Intermedio', 12, 45, 101);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Pilates Mat', 'Rafforzamento del core e miglioramento posturale', 'Principiante', 18, 55, 151);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Body Building', 'Allenamento per ipertrofia muscolare e forza', 'Avanzato', 10, 90, 201);

insert into abbonamento(data_fine, data_inizio, durata_in_mesi, id, prezzo, tipo_di_abbonamento) values('2025-01-31', '2025-01-01', 1, nextval('abbonamento_seq'), 35, 'mensile');
insert into abbonamento(data_fine, data_inizio, durata_in_mesi, id, prezzo, tipo_di_abbonamento) values('2025-01-31', '2025-01-01', 3, nextval('abbonamento_seq'), 90, 'trimestrale');
insert into abbonamento(data_fine, data_inizio, durata_in_mesi, id, prezzo, tipo_di_abbonamento) values('2025-01-31', '2025-01-01', 6, nextval('abbonamento_seq'), 160, 'semestrale');
insert into abbonamento(data_fine, data_inizio, durata_in_mesi, id, prezzo, tipo_di_abbonamento) values('2025-01-31', '2025-01-01', 12, nextval('abbonamento_seq'), 300, 'annuale');
