#!/usr/bin/env bash
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE csmarttest;

    CREATE ROLE csmartuser WITH LOGIN PASSWORD 'csmartuser';

    GRANT ALL ON DATABASE csmarttest TO csmartuser;
    GRANT ALL ON ALL TABLES IN SCHEMA public to csmartuser;
EOSQL