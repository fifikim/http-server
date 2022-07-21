require "http"

module Requests
  def self.get(path, headers = {})
    base_url = "#{PROTOCOL}://#{HOSTNAME}:#{PORT}"
    Response.new(HTTP.get("#{base_url}#{path}", headers: headers))
  end

  def self.head(path, headers = {})
    base_url = "#{PROTOCOL}://#{HOSTNAME}:#{PORT}"
    Response.new(HTTP.head("#{base_url}#{path}", headers: headers))
  end

  def self.post(path, body="", headers = {})
    base_url = "#{PROTOCOL}://#{HOSTNAME}:#{PORT}"
    Response.new(HTTP.post("#{base_url}#{path}", body: body, headers: headers))
  end

  def self.put(path, body="", headers = {})
    base_url = "#{PROTOCOL}://#{HOSTNAME}:#{PORT}"
    Response.new(HTTP.put("#{base_url}#{path}", body: body, headers: headers))
  end

  def self.delete(path, headers = {})
    base_url = "#{PROTOCOL}://#{HOSTNAME}:#{PORT}"
    Response.new(HTTP.delete("#{base_url}#{path}", headers: headers))
  end

  def self.options(path)
    base_url = "#{PROTOCOL}://#{HOSTNAME}:#{PORT}"
    Response.new(HTTP.options("#{base_url}#{path}"))
  end
end
