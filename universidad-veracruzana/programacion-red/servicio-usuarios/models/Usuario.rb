class Usuario < ActiveRecord::Base
    validates_uniqueness_of :nombre, :correo
    validates_presence_of :nombre, :correo
    
    def to_json
        super(:except => :password)
        #super(:except => [:password, :created_at, :updated_at])
        #super(:only => [:nombre, :correo, :descripcion])
    end
end