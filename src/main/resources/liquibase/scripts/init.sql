-- liquibase formatted sql

-- changeset Anokhin_ED:1
CREATE TABLE device_type (
    id SERIAL PRIMARY KEY,
    device_type_name VARCHAR(255)
);

CREATE TABLE model_type (
    id SERIAL PRIMARY KEY,
    model_type_name VARCHAR(255),
    device_type_id INTEGER REFERENCES device_type(id)
);

CREATE TABLE Model (
    id SERIAL PRIMARY KEY,
    serial_number INTEGER,
    price_in_kopecks INTEGER,
    producing_country VARCHAR(255),
    producing_company VARCHAR(255),
    color VARCHAR(255),
    dimensions VARCHAR(255),
    is_online_ordered BOOLEAN,
    is_installment_paid BOOLEAN,
    is_in_stock BOOLEAN,
    model_name VARCHAR(255),
    device_type_id INTEGER REFERENCES device_type(id),
    model_type_id INTEGER REFERENCES model_type(id)
);
CREATE TABLE model_device_specs (
    model_id INTEGER,
    spec_name VARCHAR(255),
    spec_value VARCHAR(255),
    PRIMARY KEY (model_id, spec_name),
    FOREIGN KEY (model_id) REFERENCES model (id)
);

CREATE TABLE model_type_specs (
    model_id INTEGER,
    spec_name VARCHAR(255),
    spec_value VARCHAR(255),
    PRIMARY KEY (model_id, spec_name),
    FOREIGN KEY (model_id) REFERENCES model (id)
);

CREATE TABLE model_model_specs (
    model_id INTEGER,
    spec_name VARCHAR(255),
    spec_value VARCHAR(255),
    PRIMARY KEY (model_id, spec_name),
    FOREIGN KEY (model_id) REFERENCES model (id)
);