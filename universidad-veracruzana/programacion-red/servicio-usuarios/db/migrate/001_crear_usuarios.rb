class CrearUsuarios < ActiveRecord::Migration
    def self.up
        create_table :usuarios do |t|
            t.string :nombre
            t.string :correo
            t.string :password
            t.string :descripcion

            t.timestamps :null => false
        end
    end
    
    def self.down
        drop_table :usuarios
    end
end
            