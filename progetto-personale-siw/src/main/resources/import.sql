-- Users
INSERT INTO users(id, nome, cognome, email, data_di_nascita) VALUES (nextval('users_seq'), 'Admin', 'Admin', 'admin@palestra.it', '1990-01-01');

-- Credentials
INSERT INTO credentials(id, username, password, ruolo, user_id) VALUES (nextval('credentials_seq'), 'admin', '$2a$12$/owdlKFr85U5oLlQTC7rB.l.T1iamiXx1ezJQw/1F/kxblrZlrwUa', 'ADMIN', 1);
-- la password sopra equivale a franco

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

-- Tipi di abbonamenti
insert into tipo_abbonamento(id, nome, durata_in_mesi, prezzo) values(nextval('tipo_abbonamento_seq'), 'Mensile', 1, 35);
insert into tipo_abbonamento(id, nome, durata_in_mesi, prezzo) values(nextval('tipo_abbonamento_seq'), 'Trimestrale', 3, 90);
insert into tipo_abbonamento(id, nome, durata_in_mesi, prezzo) values(nextval('tipo_abbonamento_seq'), 'Semestrale', 6, 160);
insert into tipo_abbonamento(id, nome, durata_in_mesi, prezzo) values(nextval('tipo_abbonamento_seq'), 'Annuale', 12, 300);

-- Abbonamenti (associati ai tipi di abbonamenti tramite tipo_di_abbonamento_id)
insert into abbonamento(data_fine, data_inizio, id, tipo_di_abbonamento_id) values('2025-01-31', '2025-01-01', nextval('abbonamento_seq'), 1);
insert into abbonamento(data_fine, data_inizio, id, tipo_di_abbonamento_id) values('2025-01-31', '2025-01-01', nextval('abbonamento_seq'), 51);
insert into abbonamento(data_fine, data_inizio, id, tipo_di_abbonamento_id) values('2025-01-31', '2025-01-01', nextval('abbonamento_seq'), 101);
insert into abbonamento(data_fine, data_inizio, id, tipo_di_abbonamento_id) values('2025-01-31', '2025-01-01', nextval('abbonamento_seq'), 151);