module Shared
  module Standard
    include Spinach::DSL

    step "my response should have status code 200" do
      expect(@response.status_code).to eq 200
    end

    step "my response should have status code 201" do
      expect(@response.status_code).to eq 201
    end

    step "my response should have status code 204" do
      expect(@response.status_code).to eq 204
    end

    step 'my response should have status code 301' do
      expect(@response.status_code).to eq 301
    end

    step "my response should have status code 303" do
      expect(@response.status_code).to eq 303
    end

    step "my response should have status code 400" do
      expect(@response.status_code).to eq 400
    end

    step "my response should have status code 404" do
      expect(@response.status_code).to eq 404
    end

    step 'my response should have status code 405' do
      expect(@response.status_code).to eq 405
    end

    step 'my response should have status code 406' do
      expect(@response.status_code).to eq 406
    end

    step "my response should have status code 415" do
      expect(@response.status_code).to eq 415
    end

    step 'my response should return text' do
      expect(@response.content_type).to eq "text/plain;charset=utf-8"
    end

    step "my response should return html" do
      expect(@response.content_type).to eq "text/html;charset=utf-8"
    end

    step "my response should return json" do
      expect(@response.content_type).to eq "application/json;charset=utf-8"
    end

    step 'my response should return XML' do
      expect(@response.content_type).to eq "application/xml;charset=utf-8"
    end

    step "my response should have a non-empty body" do
      expect(@response.body.length).to be > 0
    end

    step 'my response should have an empty body' do
      expect(@response.body).to be_empty
    end

    step "I make a valid POST request to create a to-do task" do
      @response = Requests.post("/todo", '{"task":"a new task"}', {
        "Content-Type": "application/json"
      })
    end
  end
end
