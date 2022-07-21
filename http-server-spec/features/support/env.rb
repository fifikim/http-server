require "rspec/expectations"
require "yaml"

file_path = File.expand_path(File.join(File.dirname(__FILE__), "config.yml"))
yaml = YAML.load_file(file_path)
HOSTNAME = yaml["server"]["hostname"]
PORT = yaml["server"]["port"]
PROTOCOL = yaml["server"]["protocol"]
