-- Create templates table
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE templates (
    template_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    template_name VARCHAR(255) UNIQUE NOT NULL,
    template_content TEXT NOT NULL,
    json_schema   JSONB,
    description   TEXT,
    created_at    BIGINT NOT NULL,
    updated_at    BIGINT
);

-- Insert sample template
INSERT INTO templates (template_name, template_content, json_schema, description, created_at, updated_at)
VALUES (
    'invoice_template_v1',
    '<html><body><h1>Invoice</h1><p>Customer: {{customerName}}</p><table>{{#each items}}<tr><td>{{name}}</td><td>{{price}}</td></tr>{{/each}}</table></body></html>',
    '{
       "type": "object",
       "properties": {
         "customerName": { "type": "string" },
         "items": {
           "type": "array",
           "items": {
             "type": "object",
             "properties": {
               "name": { "type": "string" },
               "price": { "type": "number" },
               "quantity": { "type": "number" }
             }
           }
         }
       }
     }',
    'Sample invoice template',
    EXTRACT(EPOCH FROM now()) * 1000,
    EXTRACT(EPOCH FROM now()) * 1000
);
