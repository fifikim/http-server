require "sinatra"

set :port, 5000

head "/head_request" do
  "Hello world"
end

get "/head_request" do
  status 405
  headers "Allow" => "HEAD, OPTIONS"
  ""
end

get "/html_response" do
  content_type :html
  "<html><body><p>HTML Response</p></body></html>"
end

get "/json_response" do
  content_type "application/json;charset=utf-8"
  { key1: 'value1', key2: 'value2' }.to_json
end

get "/redirect" do
  redirect "/simple_get", 301
end

get "/simple_get" do
end

get "/simple_get_with_body" do
  "Hello world"
end

get "/text_response" do
  content_type "text/plain"
  'text response'
end

get "/xml_response" do
  content_type "application/xml"
  "<note><body>XML Response</body></note>"
end

options "/method_options" do
  headers "Allow" => "GET, HEAD, OPTIONS"
  ""
end

options "/method_options2" do
  headers "Allow" => "GET, HEAD, OPTIONS, PUT, POST"
  ""
end

post "/echo_body" do
  request.body
end

get "*" do
  filePath = "web#{request.path}"
  halt send_file(filePath) if File.exists? filePath
  error 404
end
