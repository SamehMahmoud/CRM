CREATE TABLE APP_USER(
  id varchar(36) PRIMARY KEY ,
  username varchar(50) UNIQUE NOT NULL,
  password_hash varchar NOT NULL ,
  phone varchar(50),
  email varchar(50) UNIQUE NOT NULL,
  first_name varchar(50) NULL,
  last_name varchar(50) null,
  status varchar(20) NOT NULL,
  created_at timestamptz NOT NULL
);


CREATE TABLE TENANT(
  id varchar(36) PRIMARY KEY ,
  name varchar(50) NOT NULL,
  phone varchar(50),
  email varchar(50) UNIQUE NOT NULL ,
  status varchar(20) NOT NULL,
  created_at timestamptz NOT NULL,
  created_by varchar(36) NOT NULL
);
CREATE INDEX tenant_created_by_idx ON TENANT(created_by);
CREATE INDEX tenant_name_idx ON TENANT(name);



CREATE TABLE USER_TENANT(
    user_id varchar(36) NOT NULL references APP_USER(id),
    tenant_id varchar(36) NOT NULL references TENANT(id),
    role varchar(50) NOT NULL,
    primary key(user_id, tenant_id)
);
CREATE INDEX user_tenant_idx ON USER_TENANT(tenant_id);

CREATE TABLE CUSTOMER(
    id varchar(36) PRIMARY KEY ,
    name varchar(50) NOT NULL,
    tenant_id varchar(36) NOT NULL references TENANT(id),
    type varchar(20) NOT NULL,
    created_at timestamptz NOT NULL,
    created_by varchar(36) NOT NULL references APP_USER(id)
);
CREATE INDEX customer_tenant_idx on CUSTOMER(tenant_id);
CREATE INDEX customer_user_idx on CUSTOMER(created_by);

CREATE TABLE CONTACT_POINT(
    id varchar(36) PRIMARY KEY ,
    customer_id varchar(36) NOT NULL references CUSTOMER(id),
    type varchar NOT NULL,
    contact_value varchar(50) NOT NULL,
    normalized_value varchar(50) NOT NULL,
    tenant_id varchar(36) NOT NULL references TENANT(id),
    created_at timestamptz NOT NULL,
    created_by varchar(36) NOT NULL references APP_USER(id)
);
CREATE UNIQUE INDEX contact_point_email_unique_idx ON CONTACT_POINT(tenant_id, normalized_value) WHERE type = 'EMAIL';
CREATE INDEX contact_point_customer_idx on CONTACT_POINT(customer_id);
CREATE INDEX contact_point_nval_idx ON CONTACT_POINT(normalized_value);


CREATE TABLE REFRESH_TOKEN(
  id varchar(36) PRIMARY KEY ,
  user_id varchar(36) NOT NULL references APP_USER(id),
  token_hash varchar(255) NOT NULL UNIQUE,
  expires_at timestamptz NOT NULL,
  created_at timestamptz NOT NULL,
  revoked_at timestamptz 
);

CREATE INDEX refresh_token_user_idx ON REFRESH_TOKEN(user_id);
CREATE INDEX refresh_token_expiry_idx ON REFRESH_TOKEN(expires_at);

CREATE EXTENSION IF NOT EXISTS pg_cron;

SELECT cron.schedule('cleanup-expired-refresh-tokens', '*/15 * * * *', $$ DELETE FROM REFRESH_TOKEN WHERE expires_at <= now() $$);