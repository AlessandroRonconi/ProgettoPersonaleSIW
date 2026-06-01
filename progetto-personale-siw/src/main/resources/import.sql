-- Users
INSERT INTO users(id, nome, cognome, email, data_di_nascita) VALUES (nextval('users_seq'), 'Admin', 'Admin', 'admin@palestra.it', '1990-01-01');
INSERT INTO users(id, nome, cognome, email, data_di_nascita) VALUES (nextval('users_seq'), 'Giorgio', 'Bianchi', 'giorgiobianchi@gmail.com', '1986-05-06');

-- Credentials
INSERT INTO credentials(id, username, password, ruolo, user_id) VALUES (nextval('credentials_seq'), 'admin', '$2a$12$/owdlKFr85U5oLlQTC7rB.l.T1iamiXx1ezJQw/1F/kxblrZlrwUa', 'ADMIN', 1);
INSERT INTO credentials(id, username, password, ruolo, user_id) VALUES (nextval('credentials_seq'), 'giorgio86', '$2a$12$7Vc.lGWRXJGmGZPDJXRNyexebBwMVcFhDa2GASJefTwvKPg1P8lmK', 'USER', 51);
-- la password sopra equivale a franco, quella sotto a sus

-- Istruttori
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Esposito', 'Marco', 'Istruttore di funzionale');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Rossi', 'Laura', 'Istruttore di yoga');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Bianchi', 'Andrea', 'Istruttore di crossfit');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Verdi', 'Sofia', 'Istruttore di pilates');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Ferrari', 'Luca', 'Istruttore di body building');
insert into istruttore(id, cognome, nome, specialita) values(nextval('istruttore_seq'), 'Neri', 'Eleonora', 'Maestra di Kickboxing');

-- Personal Trainers
INSERT INTO personal_trainer(id, nome, cognome, specialita, tariffa_oraria) VALUES (nextval('personal_trainer_seq'), 'Alessandro', 'Marini', 'bodybuilding', 40);
INSERT INTO personal_trainer(id, nome, cognome, specialita, tariffa_oraria) VALUES (nextval('personal_trainer_seq'), 'Elena', 'Santoro', 'pilates', 35);
INSERT INTO personal_trainer(id, nome, cognome, specialita, tariffa_oraria) VALUES (nextval('personal_trainer_seq'), 'Roberto', 'Gallo', 'crossfit', 45);
INSERT INTO personal_trainer(id, nome, cognome, specialita, tariffa_oraria) VALUES (nextval('personal_trainer_seq'), 'Giulia', 'Rizzo', 'yoga', 30);
INSERT INTO personal_trainer(id, nome, cognome, specialita, tariffa_oraria) VALUES (nextval('personal_trainer_seq'), 'Federico', 'Leone', 'preparazione atletica e kickboxing', 50);

-- Corsi (associati agli istruttori tramite istruttore_id)
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Funzionale Avanzato', 'Allenamento completo per tutto il corpo con esercizi funzionali.', 'Avanzato', 15, 60, 1);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Yoga Dolce', 'Lezioni di yoga per rilassamento e flessibilità.', 'Principiante', 20, 75, 51);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'CrossFit Intenso', 'Allenamento ad alta intensità per tutto il corpo.', 'Intermedio', 12, 45, 101);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Pilates Mat', 'Rafforzamento del core e miglioramento posturale.', 'Principiante', 18, 55, 151);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Body Building', 'Allenamento per ipertrofia muscolare e forza.', 'Avanzato', 10, 90, 201);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Kickboxing Bambini', 'Sport da combattimento adatto ai più giovani.', 'Principiante/Intermedio', 15, 60, 251);
insert into corso(id, nome, descrizione, livello, capacita, durata_lezione, istruttore_id) values(nextval('corso_seq'), 'Kickboxing Adulti', 'Corso ad alta intensità che unisce tecniche di calcio e pugno per migliorare forza, agilità e coordinazione.', 'Intermedio/Avanzato', 10, 60, 251);

-- Orari dei corsi
insert into corso_orari(corso_id, giorno, orario) values (1, 'Lunedì', '09:00-10:00');
insert into corso_orari(corso_id, giorno, orario) values (1, 'Mercoledì', '09:00-10:00');
insert into corso_orari(corso_id, giorno, orario) values (1, 'Venerdì', '09:00-10:00');

insert into corso_orari(corso_id, giorno, orario) values (51, 'Martedì', '18:00-19:15');
insert into corso_orari(corso_id, giorno, orario) values (51, 'Giovedì', '18:00-19:15');

insert into corso_orari(corso_id, giorno, orario) values (101, 'Lunedì', '18:15-19:00');
insert into corso_orari(corso_id, giorno, orario) values (101, 'Mercoledì', '18:15-19:00');
insert into corso_orari(corso_id, giorno, orario) values (101, 'Sabato', '11:00-11:45');

insert into corso_orari(corso_id, giorno, orario) values (151, 'Martedì', '10:00-10:55');
insert into corso_orari(corso_id, giorno, orario) values (151, 'Giovedì', '10:00-10:55');

insert into corso_orari(corso_id, giorno, orario) values (201, 'Lunedì', '20:00-21:30');
insert into corso_orari(corso_id, giorno, orario) values (201, 'Venerdì', '20:00-21:30');

insert into corso_orari(corso_id, giorno, orario) values (251, 'Martedì', '19:15-20:15');
insert into corso_orari(corso_id, giorno, orario) values (251, 'Giovedì', '19:15-20:15');
insert into corso_orari(corso_id, giorno, orario) values (251, 'Sabato', '19:15-20:15');

insert into corso_orari(corso_id, giorno, orario) values (301, 'Lunedì', '19:00-20:00');
insert into corso_orari(corso_id, giorno, orario) values (301, 'Mercoledì', '19:00-20:00');
insert into corso_orari(corso_id, giorno, orario) values (301, 'Venerdì', '19:00-20:00');

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